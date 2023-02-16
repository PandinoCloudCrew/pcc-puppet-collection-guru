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

import static pcc.puppet.guru.configuration.HttpHeaders.REQUESTER;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.tracing.annotation.SpanTag;
import javax.validation.Valid;
import pcc.puppet.guru.app.Project;
import pcc.puppet.guru.collection.adapters.http.request.OrganizationCreateRequest;
import pcc.puppet.guru.collection.adapters.http.response.entity.OrganizationResponse;
import pcc.puppet.guru.collection.adapters.http.response.action.OrganizationCreateResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Client("pcc-realm-organization")
@Header(name = HttpHeaders.ACCEPT_ENCODING, value = "gzip, deflate")
@Header(
    name = HttpHeaders.USER_AGENT,
    value = "OrganizationClient/" + Project.VERSION + " (" + Project.NAME + ")")
public interface OrganizationClient {

  @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
  Mono<OrganizationCreateResponse> organizationCreate(
      @NonNull @SpanTag(REQUESTER) @Header(REQUESTER) String requester,
      @NonNull @Body @Valid OrganizationCreateRequest createCommand);

  @Get(uri = "/{organizationId}", produces = MediaType.APPLICATION_JSON)
  Mono<OrganizationResponse> findOrganization(
      @SpanTag(REQUESTER) @NonNull @Header(REQUESTER) String requester,
      @SpanTag @NonNull String organizationId);

  @Get(uri = "/{organizationId}/child", produces = MediaType.APPLICATION_JSON)
  Flux<OrganizationResponse> findChildOrganizations(
      @SpanTag(REQUESTER) @NonNull @Header(REQUESTER) String requester,
      @SpanTag @NonNull String organizationId);

  @Post(
      uri = "/{organizationId}/login",
      consumes = MediaType.APPLICATION_JSON,
      produces = MediaType.APPLICATION_JSON)
  Mono<AccessRefreshToken> organizationLogin(
      @NonNull String organizationId, @NonNull @Body UsernamePasswordCredentials credentials);
}
