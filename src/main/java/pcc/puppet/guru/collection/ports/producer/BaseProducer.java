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
package pcc.puppet.guru.collection.ports.producer;

import io.micronaut.http.MediaType;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import pcc.puppet.guru.collection.domain.Message;
import pcc.puppet.guru.collection.domain.MessageProtocol;
import pcc.puppet.guru.collection.domain.MessageStatus;

@RequiredArgsConstructor
public abstract class BaseProducer<T extends Message<?>> {

  private final String id;
  private final String type;
  private final MessageProtocol protocol;
  private final MediaType dataContentType;
  private final String source;
  private final String destination;

  public void send(T message) {
    setProperties(message);
    sendToQueue(message);
  }

  protected abstract void sendToQueue(T message);

  private void setProperties(Message<?> message) {
    message.setProducedBy(String.format("%s-%s", type, id));
    message.setDataContentType(dataContentType);
    message.setProtocol(protocol);
    message.setSource(source);
    message.setDestination(destination);
    message.setAdjoinTime(Instant.now());
    message.setStatus(MessageStatus.REQUESTED);
  }
}
