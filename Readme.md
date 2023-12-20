# Gas & Water Usage Monitoring Application

The **Gas & Water Usage Monitoring Application** is a REST API-based solution created to monitor gas, cold water, and hot water usage.

## API Endpoints

### Submit a New Measurement

- **Endpoint**: `POST /submit`
- **Summary**: Submit a new measurement for gas, cold water, and hot water usage.
- **Request Body**: Measurement object
- **Response Code**:
    - 201: Measurement successfully created
- **Response Body**: "Measurement successfully created"

### Get Measurement History

- **Endpoint**: `GET /history/{userId}`
- **Summary**: Retrieve the history of previously submitted measurements for a given user.
- **Path Variable**: userId (String)
- **Response Code**:
    - 200: Measurements history retrieved
- **Response Body**: List of Measurement objects

## Project Details

### Description

The Gas & Water Usage Monitoring Application focuses on monitoring gas, cold water, and hot water consumption through a REST API. The application ensures data integrity by validating user inputs to reject incomplete or invalid data.

### Technical Requirements

1. **Java Version**: 1.8
2. **Framework**: Spring Framework
3. **Build Tool**: Maven
4. **Database**: HSQLDB (non-persistent data storage across application launches)