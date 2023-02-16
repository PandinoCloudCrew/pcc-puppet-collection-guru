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
package pcc.puppet.guru.collection.adapters.http.response.action;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Introspected
public class ConsumerPassportCreateResponse {

  @NonNull private String memberId;
  @NonNull private String username;
  @NonNull private MemberCreateResponse member;
  @NonNull private OrganizationCreateResponse organization;
  @NonNull private DepartmentCreateResponse department;
  @NonNull private AccessRefreshToken accessRefreshToken;

  public ConsumerPassportCreateResponse organization(
      OrganizationCreateResponse organizationCreateResponse) {
    this.organization = organizationCreateResponse;
    return this;
  }

  public ConsumerPassportCreateResponse department(
      DepartmentCreateResponse departmentCreateResponse) {
    this.department = departmentCreateResponse;
    return this;
  }

  public ConsumerPassportCreateResponse member(MemberCreateResponse memberCreateResponse) {
    this.member = memberCreateResponse;
    return this;
  }

  public ConsumerPassportCreateResponse token(AccessRefreshToken token) {
    this.accessRefreshToken = token;
    return this;
  }

  public String organizationId() {
    return this.organization.getId();
  }

  public String departmentId() {
    return this.department.getId();
  }

  public String memberId() {
    return this.member.getId();
  }
}
