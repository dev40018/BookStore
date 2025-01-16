DROP TABLE IF EXISTS "books";
DROP TABLE IF EXISTS "authors";

CREATE TABLE "authors"(

    "id" BIGINT  NOT NULL,
    "name" TEXT,
    "age" INTEGER,
    CONSTRAINT "authors_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "books"(

    "isbn" TEXT   NOT NULL,
    "title" TEXT,
    "author_id" BIGINT,
    CONSTRAINT "books_pkey" FOREIGN KEY("isbn"),
    CONSTRAINT "fk_author" FOREIGN KEY(author_id) REFERENCES authors(id)
);