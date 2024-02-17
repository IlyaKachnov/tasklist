CREATE TABLE public.task
(
    id          SERIAL PRIMARY KEY,
    name        varchar(100),
    description text,
    status      boolean default false
);
