CREATE TABLE IF NOT EXISTS destinations (
    id SERIAL PRIMARY KEY,
    distance NUMERIC(10, 2),
    country VARCHAR(25),
    city VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS cargo_types (
  id SERIAL PRIMARY KEY,
  title VARCHAR(50),
  required_experience NUMERIC(3, 1),
  cost_per_kg MONEY
);

CREATE TABLE IF NOT EXISTS applications (
    id SERIAL PRIMARY KEY,
    weight NUMERIC(10, 2),
    cargo_type_id INTEGER,

    FOREIGN KEY (cargo_type_id) REFERENCES cargo_types(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS drivers (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  earnings MONEY,
  driving_experience NUMERIC(3, 1)
);

CREATE TABLE IF NOT EXISTS cars (
  id SERIAL PRIMARY KEY,
  model VARCHAR(25),
  condition INTEGER,
  is_on_service BIT,
  manufacturer VARCHAR(25),
  load_capacity NUMERIC(10, 2)
);

CREATE TABLE IF NOT EXISTS routes (
    id SERIAL PRIMARY KEY,
    application_id INTEGER,
    driver_id INTEGER,
    car_id INTEGER,

    FOREIGN KEY (application_id) REFERENCES applications(id) ON DELETE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES drivers(id) ON DELETE CASCADE,
    FOREIGN KEY (car_id) REFERENCES cars(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS completed_routes (
    id SERIAL PRIMARY KEY,
    begin_date DATE,
    end_date DATE,
    route_id INTEGER,

    FOREIGN KEY (route_id) REFERENCES routes(id) ON DELETE CASCADE
);