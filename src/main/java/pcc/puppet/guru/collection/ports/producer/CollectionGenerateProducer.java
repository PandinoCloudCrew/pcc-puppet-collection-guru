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

import com.github.shamil.Xid;
import io.micronaut.http.MediaType;
import jakarta.inject.Singleton;
import pcc.puppet.guru.collection.domain.MessageProtocol;
import pcc.puppet.guru.collection.ports.command.CollectionGenerateCommand;

@Singleton
public class CollectionGenerateProducer extends BaseProducer<CollectionGenerateCommand> {

  public static final String SOURCE = "collection-generate-initiator";
  public static final String DESTINATION = "collection-generate-pending";
  public static final String TYPE = CollectionGenerateCommand.class.getTypeName();
  public static final MediaType CONTENT_TYPE = MediaType.APPLICATION_JSON_TYPE;
  public static final MessageProtocol PROTOCOL = MessageProtocol.AMQP;
  private final JmsProducer jmsProducer;

  public CollectionGenerateProducer(JmsProducer jmsProducer) {
    super(Xid.string(), TYPE, PROTOCOL, CONTENT_TYPE, SOURCE, DESTINATION);
    this.jmsProducer = jmsProducer;
  }

  @Override
  protected void sendToQueue(CollectionGenerateCommand message) {
    jmsProducer.send(message);
  }
}
