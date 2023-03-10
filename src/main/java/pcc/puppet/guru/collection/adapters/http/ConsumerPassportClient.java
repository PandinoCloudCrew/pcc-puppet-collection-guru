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
package pcc.puppet.guru.collection.adapters.http;

import static io.micronaut.http.HttpHeaders.ACCEPT_ENCODING;
import static io.micronaut.http.HttpHeaders.USER_AGENT;
import static io.micronaut.http.MediaType.APPLICATION_JSON;
import static pcc.puppet.guru.app.Project.NAME;
import static pcc.puppet.guru.app.Project.VERSION;
import static pcc.puppet.guru.configuration.HttpHeaders.REQUESTER;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.tracing.annotation.SpanTag;
import pcc.puppet.guru.collection.adapters.http.request.ConsumerPassportCreateRequest;
import pcc.puppet.guru.collection.adapters.http.response.action.ConsumerPassportCreateResponse;
import reactor.core.publisher.Mono;

@Client("${micronaut.http.services.pcc-realm-passport.path}")
@Header(name = ACCEPT_ENCODING, value = "gzip, deflate")
@Header(name = USER_AGENT, value = "ConsumerPassportClient/" + VERSION + " (" + NAME + ")")
public interface ConsumerPassportClient {

  @Post(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
  Mono<ConsumerPassportCreateResponse> createConsumerPassport(
      @NonNull @SpanTag(REQUESTER) @Header(REQUESTER) String requester,
      @NonNull ConsumerPassportCreateRequest passportCommand);
}
