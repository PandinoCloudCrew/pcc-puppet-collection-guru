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

import static pcc.puppet.guru.configuration.HttpHeaders.ORGANIZATION;
import static pcc.puppet.guru.configuration.HttpHeaders.REQUESTER;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.tracing.annotation.SpanTag;
import javax.validation.Valid;
import pcc.puppet.guru.app.Project;
import pcc.puppet.guru.collection.adapters.http.request.DepartmentCreateRequest;
import pcc.puppet.guru.collection.adapters.http.response.entity.DepartmentResponse;
import pcc.puppet.guru.collection.adapters.http.response.action.DepartmentCreateResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Client("pcc-realm-department")
@Header(name = HttpHeaders.ACCEPT_ENCODING, value = "gzip, deflate")
@Header(
    name = HttpHeaders.USER_AGENT,
    value = "DepartmentClient/" + Project.VERSION + " (" + Project.NAME + ")")
public interface DepartmentClient {

  @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
  Mono<DepartmentCreateResponse> departmentCreate(
      @NonNull @SpanTag(REQUESTER) @Header(REQUESTER) String requester,
      @NonNull @SpanTag(ORGANIZATION) @Header(ORGANIZATION) String organizationId,
      @NonNull @Body @Valid DepartmentCreateRequest createCommand);

  @Get(uri = "/{departmentId}", consumes = MediaType.APPLICATION_JSON)
  Mono<DepartmentResponse> findDepartment(
      @NonNull @SpanTag(REQUESTER) @Header(REQUESTER) String requester,
      @NonNull @SpanTag(ORGANIZATION) @Header(ORGANIZATION) String organizationId,
      @NonNull String departmentId);

  @Get(uri = "/{departmentId}/child", consumes = MediaType.APPLICATION_JSON)
  Flux<DepartmentResponse> findChildDepartments(
      @NonNull @SpanTag(REQUESTER) @Header(REQUESTER) String requester,
      @NonNull @SpanTag(ORGANIZATION) @Header(ORGANIZATION) String organizationId,
      @SpanTag @NonNull String departmentId);
}
