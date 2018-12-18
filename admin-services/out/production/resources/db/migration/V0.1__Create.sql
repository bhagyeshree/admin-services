CREATE SEQUENCE admin_sequence;

CREATE TABLE badge
(
  id bigint NOT NULL,
  status character varying(255),
  CONSTRAINT badge_pkey PRIMARY KEY (id)
);
