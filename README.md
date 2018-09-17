# box.io

TODO:

- Change `findViewById` calls with `ButterKnife` injection
- Add cache for received data

- Write unit tests to test business logic Presenter and MVP-View
- Save state in case of orientation changes. This can be implemented via Repository pattern as singleton or using ViewModel from android arch components
- Use GSON for de|serialization the JSON data
- Use `Retrofit + OkHttp` to fire up web requests to RESTful API
- If needed, test the web requests using `MockWebServer` or custom `interceptor` in OkHttp for simple cases.
- Use Picasso or Glide for image loading|cache|changes
- Check Lint warnings and recommendations
