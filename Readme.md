# Gas & Water Usage Monitoring API

## Submit Current Measurements

Submit the current gas, cold water, and hot water usage measurements for a given user.

- **URL:** `/submit`
- **HTTP Method:** `POST`

### Request

#### Body

- Measurement (Request Body)
  - gasUsage (double) - Gas usage in cubic meters.
  - coldWaterUsage (double) - Cold water usage in liters.
  - hotWaterUsage (double) - Hot water usage in liters.

### Response

- **Status Code:** 201 - Created
- **Description:** Current measurements successfully submitted.

## Get Measurement History

Retrieve the history of previously submitted measurements for a given user.

- **URL:** `/history/{userId}`
- **HTTP Method:** `GET`

### Path Parameters

- userId (Path Variable)

### Response

- **Status Code:** 200 - OK
- **Description:** History of previously submitted measurements retrieved.

## Get Measurement History with Pagination

Retrieve the history of measurements for a specific user with pagination.

- **URL:** `/history/{userId}/paged`
- **HTTP Method:** `GET`

### Path Parameters

- userId (Path Variable) - User identifier

### Query Parameters

- page (Request Parameter, Default: 0) - Page number for pagination.
- size (Request Parameter, Default: 10) - Number of items per page.

### Response

- **Status Code:** 200 - OK
- **Description:** Measurements history retrieved with pagination.

### Request

#### Query Parameters

- page (int, Default: 0)
- size (int, Default: 10)

### Response

- **Status Code:** 200 - OK
- **Description:** Measurements history retrieved with pagination.

## Additional Features

- **Validation:** User inputs are validated to reject incomplete or invalid data.

## Actuator Endpoints

- Health: `/actuator/health`

## Monitoring with Prometheus

The application exposes metrics at `/actuator/prometheus` for monitoring with Prometheus.

## Code Coverage

- **JaCoCo:** The code coverage is maintained above 80%.
Simply run Maven verify and open the file target/site/jacoco/index.html in your browser. 

## Testing

- The code is thoroughly tested to ensure proper functionality and reliability.

