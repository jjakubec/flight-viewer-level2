# FlightViewer Application

## Build Instructions
To build the application, run the following Maven command:

```sh
mvn clean install -DskipTests
```
This command will clean the project, install the dependencies, and skip the tests to speed up the build process.

## Running the Application

To run the application, run the following Maven command:

```sh
mvn spring-boot:run
```

This command will start the Spring Boot application.

## 🛫 Flight Departures Form

The **Departures Form** allows users to search for departing flights from a specific airport within a defined time range.

### ✅ Accessing the Form
Open the form by visiting:

http://localhost:8080/departures-form


### ✅ How to Use
1️⃣ Enter the **airport ICAO code (Letiště)** (e.g., `LKPR` for Prague Airport).  
2️⃣ Select the **start time (Začátek)** and **end time** for your search.  
3️⃣ Click **"Zobrazit odlety (Konec)"** to view the results.

### ✅ Viewing Departures
After submitting the form, a table displaying departure details will appear, including:
- Flight ICAO24 identifier
- Callsign
- Departure and arrival airports
- Time of first and last detection
- Distance metrics

---

This page provides a user-friendly interface for retrieving real-time departure data using OpenSky Network API. 🚀  

