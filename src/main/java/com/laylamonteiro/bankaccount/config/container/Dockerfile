FROM postgres:latest as dumper

ENV PG_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV PGDATA=/data

RUN ["/usr/local/bin/docker-entrypoint.sh", "postgres"]

FROM postgres:latest

COPY --from=dumper /data $PGDATA