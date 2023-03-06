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
package pcc.puppet.guru.collection.ports.command;

import com.github.shamil.Xid;
import io.micronaut.core.annotation.Introspected;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import pcc.puppet.guru.app.Project;
import pcc.puppet.guru.collection.domain.Message;
import pcc.puppet.guru.collection.domain.MessageType;
import pcc.puppet.guru.collection.ports.api.request.CollectionGenerateRequest;

@Introspected
@Jacksonized
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(builderMethodName = "innerBuilder")
public class CollectionGenerateCommand extends Message<CollectionGenerateRequest> {
  public static final String NAME = "collection-generate-command";
  public static final String VERSION = "1.0.0";

  public static CollectionGenerateCommandBuilder<?, ?> builder() {
    return innerBuilder()
        .id(Xid.string())
        .name(NAME)
        .type(MessageType.COMMAND)
        .dataSchema(Project.schema(NAME, VERSION))
        .version(VERSION);
  }
}
