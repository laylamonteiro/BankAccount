# sudo service docker stop
# systemctl restart docker
# docker-compose up -d
# docker-compose down -v
# docker rm -f $(docker ps -a -q)
# docker volume rm $(docker volume ls -q)
# docker system prune -a

FROM postgres:latest as dumper

ENV PG_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV PGDATA=/data

RUN ["/usr/local/bin/docker-entrypoint.sh", "postgres"]

FROM postgres:latest

COPY --from=dumper /data $PGDATA