/*
 * Copyright 2022 Pandino Cloud Crew (C)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pcc.puppet.guru.collection.ports.consumer;

import static pcc.puppet.guru.collection.domain.MessageStatus.EXPIRED;
import static pcc.puppet.guru.collection.domain.MessageStatus.PROCESSED;
import static pcc.puppet.guru.collection.domain.MessageStatus.PROCESSING;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pcc.puppet.guru.collection.domain.Message;
import pcc.puppet.guru.collection.domain.MessageStatus;
import pcc.puppet.guru.collection.service.CollectionProgressService;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseConsumer {

  private final String id;
  private final String type;
  private final int retries;
  private final CollectionProgressService progressService;

  public void handle(Message<?> message) {
    take(message);
    run(message);
    if (message.getReceiveCount() > retries) {
      release(message, EXPIRED);
    } else {
      release(message, PROCESSED);
    }
  }

  protected abstract void run(Message<?> message);

  private void take(Message<?> message) {
    message.setReceiveCount(message.getReceiveCount() + 1);
    message.setConsumedBy(String.format("%s-%s", type, id));
    message.setProcessingStart(Instant.now());
    message.setStatus(PROCESSING);
  }

  private void release(Message<?> message, MessageStatus status) {
    message.setProcessingEnd(Instant.now());
    message.setStatus(status);
    progressService.add(message);
    if (progressService.isCompleted(message.getCollectionId())) {
      log.info("collection {} completed", message.getCollectionId());
    }
  }
}
