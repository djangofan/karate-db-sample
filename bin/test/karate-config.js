function fn() {
  var env = karate.env == null ? "dev" : karate.env;
  karate.log('karate.env property is: ', env);

  var config = {
    env: env,
    data: read("classpath:data.json")[env]
  };
  return config;
}
