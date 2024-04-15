ALTER TABLE completed_routes
DROP COLUMN route_id;

ALTER TABLE completed_routes
ADD COLUMN application_id INTEGER,
ADD COLUMN driver_id INTEGER,
ADD COLUMN car_id INTEGER,
ADD COLUMN destination_id INTEGER;

ALTER TABLE completed_routes
ADD CONSTRAINT completed_routes_application_id_fkey
FOREIGN KEY (application_id)
REFERENCES applications(id)
ON DELETE CASCADE,

ADD CONSTRAINT completed_routes_driver_id_fkey
FOREIGN KEY (driver_id)
REFERENCES drivers(id)
ON DELETE CASCADE,

ADD CONSTRAINT completed_routes_car_id_fkey
FOREIGN KEY (car_id)
REFERENCES cars(id)
ON DELETE CASCADE,

ADD CONSTRAINT completed_routes_destination_id_fkey
FOREIGN KEY (destination_id)
REFERENCES destinations(id)
ON DELETE CASCADE;
