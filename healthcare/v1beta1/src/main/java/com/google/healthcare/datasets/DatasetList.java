/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.healthcare.datasets;

// [START healthcare_list_datasets]

import com.google.HealthcareQuickstart;
import com.google.api.services.healthcare.v1beta1.model.Dataset;
import com.google.api.services.healthcare.v1beta1.model.ListDatasetsResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DatasetList {
  public static void listDatasets(String projectId, String cloudRegion) throws IOException {
    cloudRegion = Optional.of(cloudRegion).orElse("us-central1");
    String parentName = String.format("projects/%s/locations/%s", projectId, cloudRegion);
    ListDatasetsResponse response =
        HealthcareQuickstart.getCloudHealthcareClient()
            .projects()
            .locations()
            .datasets()
            .list(parentName)
            .execute();
    List<Dataset> datasets = response.getDatasets();
    if (datasets == null) {
      System.out.println("Retrieved 0 datasets");
      return;
    }
    System.out.println("Retrieved " + datasets.size() + " datasets");
    for (Dataset dataset : datasets) {
      System.out.println("  - " + dataset.getName());
    }
  }
}
// [END healthcare_list_datasets]
