# Warning: this is an example self-signed certificate generation, not one that should be used in production. Be careful how you use this...

EXAMPLE_PASSWORD=example

# -------

# CA setup

# Create CA key and cert
openssl req -newkey rsa:2048 -nodes -keyout ca/ca.key -x509 -days 365 -out ca/ca.crt -config ca.cnf

# Inspect cert
openssl x509 -text -noout -in ca/ca.crt

# -------

# Server setup

# Create a Server key and csr
openssl req -newkey rsa:2048 -nodes -keyout server/server.key -out server/server.csr -config server.cnf

# Create a signed server certificate
openssl x509 -req -in server/server.csr -CA ca/ca.crt -CAkey ca/ca.key -CAcreateserial -out server/server.crt -days 1024 -sha256

# Verify signed certificate
openssl x509 -text -noout -in server/server.crt

# Create PFX (P12) with server key, server cert and ca cert
openssl pkcs12 -export -passout pass:$EXAMPLE_PASSWORD -out server/server-keystore.pfx -inkey server/server.key -in server/server.crt -name server

# Create PFX (P12) with just the ca cert
keytool -importcert -storepass $EXAMPLE_PASSWORD -trustcacerts -noprompt -keystore server/server-truststore.pfx -storetype pkcs12 -alias server -file ca/ca.crt

# Inspect pfx
openssl pkcs12 -info -passin pass:$EXAMPLE_PASSWORD -in server/server-keystore.pfx -nodes
openssl pkcs12 -info -passin pass:$EXAMPLE_PASSWORD -in server/server-truststore.pfx -nodes
keytool -list -v -keystore server/server-truststore.pfx -storepass $EXAMPLE_PASSWORD -storetype PKCS12

# -------

# User setup

# Create a Server key and csr
openssl req -newkey rsa:2048 -nodes -keyout user/user.key -out user/user.csr -config user.cnf

# Create a signed server certificate
openssl x509 -req -in user/user.csr -CA ca/ca.crt -CAkey ca/ca.key -CAcreateserial -out user/user.crt -days 1024 -sha256

# Inspect signed certificate
openssl x509 -text -noout -in user/user.crt