ALTER TABLE routes
ADD COLUMN destination_id INTEGER;

ALTER TABLE routes
ADD CONSTRAINT routes_destination_id_fkey
FOREIGN KEY (destination_id)
REFERENCES destinations(id)
ON DELETE CASCADE;
