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
package pcc.puppet.guru.collection.domain;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.MediaType;
import java.time.Instant;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Introspected
@ToString
public abstract class Message<T> {
  protected String id;
  protected String subject;
  protected String name;
  protected MessageType type;
  protected String dataSchema;
  protected String version;
  protected String collectionId;
  protected int recordNumber;
  protected int receiveCount;
  protected MessageStatus status;
  protected String source;
  protected String destination;
  protected MessageProtocol protocol;
  protected T data;
  protected MediaType dataContentType;
  protected String owner;
  protected String consumedBy;
  protected String producedBy;
  protected Instant adjoinTime;
  protected Instant disjoinTime;
  protected Instant processingStart;
  protected Instant processingEnd;
}
