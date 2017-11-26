FROM minijava8

LABEL maintainer="bamboo <892078604@qq.com>"

ENV profile=prod SERVER_PORT=8080 JAVA_OPTS=


CMD	java -jar -Djava.net.preferIPv4Stack=true \
	--server.port=${SERVER_PORT} \
	--spring.profiles.active=${profile}


HEALTHCHECK --interval=10s --timeout=3s \
	CMD curl -f http://localhost:${SERVER_PORT}/health 2>/dev/null || exit 1