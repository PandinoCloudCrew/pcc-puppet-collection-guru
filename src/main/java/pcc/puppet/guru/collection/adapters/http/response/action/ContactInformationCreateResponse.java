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
import lombok.Builder;
import lombok.Data;
import pcc.puppet.guru.generator.values.CityNameStrategy;
import pcc.puppet.guru.generator.values.CountryNameStrategy;
import pcc.puppet.guru.generator.values.CurrencyCodeStrategy;
import pcc.puppet.guru.generator.values.EmailStrategy;
import pcc.puppet.guru.generator.values.FirstNameStrategy;
import pcc.puppet.guru.generator.values.JobPositionStrategy;
import pcc.puppet.guru.generator.values.LastNameStrategy;
import pcc.puppet.guru.generator.values.LocaleStrategy;
import pcc.puppet.guru.generator.values.ObjectIdStrategy;
import pcc.puppet.guru.generator.values.PhoneNumberStrategy;
import pcc.puppet.guru.generator.values.TimeZoneStrategy;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Data
@Builder
@Introspected
public class ContactInformationCreateResponse {

  @NonNull
  @PodamStrategyValue(ObjectIdStrategy.class)
  private String id;

  @NonNull private String ownerId;

  @NonNull
  @PodamStrategyValue(FirstNameStrategy.class)
  private String firstName;

  @NonNull
  @PodamStrategyValue(LastNameStrategy.class)
  private String lastName;

  @NonNull
  @PodamStrategyValue(PhoneNumberStrategy.class)
  private String phoneNumber;

  @NonNull
  @PodamStrategyValue(EmailStrategy.class)
  private String email;

  @NonNull
  @PodamStrategyValue(JobPositionStrategy.class)
  private String position;

  @NonNull
  @PodamStrategyValue(TimeZoneStrategy.class)
  private String zoneId;

  @NonNull
  @PodamStrategyValue(LocaleStrategy.class)
  private String locale;

  @NonNull
  @PodamStrategyValue(CountryNameStrategy.class)
  private String country;

  @NonNull
  @PodamStrategyValue(CityNameStrategy.class)
  private String city;

  @NonNull
  @PodamStrategyValue(CurrencyCodeStrategy.class)
  private String currency;

  @NonNull private String createdAt;
}
