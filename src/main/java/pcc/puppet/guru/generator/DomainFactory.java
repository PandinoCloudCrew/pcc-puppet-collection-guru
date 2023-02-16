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
package pcc.puppet.guru.generator;

import com.github.shamil.Xid;
import io.micronaut.core.annotation.Introspected;
import lombok.experimental.UtilityClass;
import pcc.puppet.guru.collection.adapters.http.request.ConsumerPassportCreateRequest;
import pcc.puppet.guru.collection.adapters.http.request.DepartmentCreateRequest;
import pcc.puppet.guru.collection.adapters.http.request.MemberCreateRequest;
import pcc.puppet.guru.collection.adapters.http.request.OrganizationCreateRequest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@UtilityClass
@Introspected
public class DomainFactory {

  private final PodamFactory factory = new PodamFactoryImpl();

  public String id() {
    return Xid.string();
  }

  public OrganizationCreateRequest organizationCreateCommand() {
    return getPojoWithFullData(OrganizationCreateRequest.class);
  }

  public DepartmentCreateRequest departmentCreateCommand() {
    return getPojoWithFullData(DepartmentCreateRequest.class);
  }

  public MemberCreateRequest memberCreateCommand() {
    return getPojoWithFullData(MemberCreateRequest.class);
  }

  public ConsumerPassportCreateRequest consumerPassportCreateCommand() {
    return getPojoWithFullData(ConsumerPassportCreateRequest.class);
  }

  private <T> T getPojoWithFullData(Class<T> classType) {
    return factory.manufacturePojoWithFullData(classType);
  }
}
