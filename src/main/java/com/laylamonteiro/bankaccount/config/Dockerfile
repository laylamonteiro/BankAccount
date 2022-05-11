FROM postgres
ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_DB bankaccountdb
COPY bankaccountdb.sql /docker-entrypoint-initdb.d/
