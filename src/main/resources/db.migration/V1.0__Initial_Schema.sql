CREATE TABLE "user"(
   "id" INTEGER PRIMARY KEY,
   "nickname" VARCHAR(45) NOT NULL,
   "email" VARCHAR(45) NOT NULL,
   "password" VARCHAR(100) NOT NULL
);

ALTER TABLE "user" ADD COLUMN type VARCHAR(45) NOT NULL default 'U';

CREATE TABLE "genre"(
    "id" INTEGER PRIMARY KEY,
    "name" VARCHAR(45) NOT NULL
);

CREATE SEQUENCE genres_id_sequence OWNED BY postgres.public.genre.id;
ALTER TABLE genre ALTER COLUMN id SET DEFAULT nextval('genres_id_sequence');
ALTER SEQUENCE genres_id_sequence INCREMENT BY 50;

CREATE TABLE "movie"(
    "id" INTEGER PRIMARY KEY,
    "name_russian" VARCHAR(100) NOT NULL,
    "name_native" VARCHAR(100) NOT NULL,
    "year_of_release" DATE NOT NULL,
    "description" VARCHAR(1000) NOT NULL,
    "rating" DECIMAL(8, 1)  NOT NULL,
    "price" DECIMAL(8, 2)  NOT NULL,
    "picture_path" VARCHAR(255) NOT NULL,
    "votes" INTEGER NOT NULL
);

CREATE SEQUENCE movies_id_sequence OWNED BY postgres.public.movie.id;
ALTER TABLE "movie" ALTER COLUMN id SET DEFAULT nextval('movies_id_sequence');
ALTER SEQUENCE movies_id_sequence INCREMENT BY 50;
ALTER TABLE "movie" ALTER COLUMN votes SET DEFAULT '100';

CREATE TABLE "country"(
  "id" INTEGER PRIMARY KEY,
  "name" VARCHAR(45) NOT NULL
);

CREATE SEQUENCE country_id_sequence OWNED BY postgres.public.country.id;
ALTER TABLE country ALTER COLUMN id SET DEFAULT nextval('country_id_sequence');
ALTER SEQUENCE country_id_sequence INCREMENT BY 50;

CREATE TABLE "review"(
     "id" INTEGER PRIMARY KEY,
     "movie_id" INTEGER NOT NULL,
     "user_id" INTEGER NOT NULL,
     "description" VARCHAR(500) NOT NULL
);

CREATE SEQUENCE review_id_sequence OWNED BY postgres.public.review.id;
ALTER TABLE review ALTER COLUMN id SET DEFAULT nextval('review_id_sequence');
ALTER SEQUENCE review_id_sequence INCREMENT BY 50;

CREATE TABLE "rating"(
     "user_id" INTEGER NOT NULL,
     "movie_id" INTEGER NOT NULL,
     "rating" DECIMAL(8, 1) NOT NULL
);

ALTER TABLE "rating" add CONSTRAINT "user_id" FOREIGN KEY("user_id") REFERENCES "user"("id");
ALTER TABLE "rating" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");

CREATE TABLE "movie_genre"(
      "movie_id" INTEGER NOT NULL,
      "genre_id" INTEGER NOT NULL
);

ALTER TABLE "movie_genre" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");
ALTER TABLE "movie_genre" add CONSTRAINT "genre_id" FOREIGN KEY("genre_id") REFERENCES "genre"("id");

CREATE TABLE "movie_country"(
        "movie_id" INTEGER NOT NULL,
        "country_id" INTEGER NOT NULL
);

ALTER TABLE "movie_country" add CONSTRAINT "country_id" FOREIGN KEY("country_id") REFERENCES "country"("id");
ALTER TABLE "movie_country" add CONSTRAINT "movie_id" FOREIGN KEY("movie_id") REFERENCES "movie"("id");