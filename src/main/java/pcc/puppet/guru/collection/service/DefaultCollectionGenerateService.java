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
package pcc.puppet.guru.collection.service;

import com.github.shamil.Xid;
import jakarta.inject.Singleton;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import pcc.puppet.guru.collection.ports.api.request.CollectionGenerateRequest;
import pcc.puppet.guru.collection.ports.api.response.CollectionGenerateResponse;
import pcc.puppet.guru.collection.ports.command.CollectionGenerateCommand;
import pcc.puppet.guru.collection.ports.producer.CollectionGenerateProducer;

@Singleton
@RequiredArgsConstructor
public class DefaultCollectionGenerateService implements CollectionGenerateService {

  private final CollectionGenerateProducer collectionGenerateProducer;

  public CollectionGenerateResponse generateCollection(String requester, CollectionGenerateRequest createRequest) {
    String collectionId = Xid.string();
    CollectionGenerateCommand.builder().data(createRequest).owner(requester).collectionId(collectionId);
    return CollectionGenerateResponse.builder()
        .id(collectionId)
        .owner(requester)
        .size(createRequest.getSize())
        .subject("type-of-collection-generated")
        .requestTime(Instant.now())
        .build();
  }
}
