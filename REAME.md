# Чтение данных API NASA 
Из публичного API NASA и скачиваем ежедневно выгружаемую им изображение или другой контент. 
Для получения token зарегистрируйтесь на https://api.nasa.gov/.

Пример ответа сервиса https://api.nasa.gov/:
```json
{
  "copyright": "Raul Villaverde",
  "date": "2022-07-10",
  "explanation": "Three thousand light-years away, a dying star throws off shells of glowing gas. This image from the Hubble Space Telescope reveals the Cat's Eye Nebula (NGC 6543), to be one of the most complex planetary nebulae known. Spanning half a light-year, the features seen in the Cat's Eye are so complex that astronomers suspect the bright central object may actually be a binary star system. The term planetary nebula, used to describe this general class of objects, is misleading. Although these objects may appear round and planet-like in small telescopes, high resolution images with large telescopes reveal them to be stars surrounded by cocoons of gas blown off in the late stages of stellar evolution. Gazing into this Cat's Eye, astronomers may well be seeing more than detailed structure, they may be seeing the fate of our Sun, destined to enter its own planetary nebula phase of evolution ... in about 5 billion years.",
  "hdurl": "https://apod.nasa.gov/apod/image/2207/CatsEye_HubbleVillaVerde_960.jpg",
  "media_type": "image",
  "service_version": "v1",
  "title": "In the Center of the Cat's Eye Nebula",
  "url": "https://apod.nasa.gov/apod/image/2207/CatsEye_HubbleVillaVerde_960.jpg"
}
```
Токен укажите в поле TOKEN класса Communication
```java
package org.ivanplehanov.nasa.api;

public class Communication {

    private static final String REMOTE_SERVICE_URL = "https://api.nasa.gov/planetary/apod?api_key=";
    private static final String TOKEN = "XXXX";

}
```
###Main классом выступает App.java который и запустит скачивание в */src/main/resources/NASAFiles/
```java
public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        
        Downloader downloader = context.getBean("downloader", Downloader.class);
        Communication communication = context.getBean("communication", Communication.class);
        InformationObject nasaToDay = communication.getNasaInformationObject();
        
        downloader.downloadFileNasa(nasaToDay);


    }
```
## Принцип работы и описание функционала
Для получения JSON и преобразования его в объект класа InformationObject я использовал зависимости:
* spring-webmvc
* jackson-databind

####Описание Communication.java
C помощью анатации @Autowired устанавливаем
зависимость между Bean'ом Communication и 
RestTemplate - вспомогательным классом для составления Http запросов.
```java
    @Autowired
    private RestTemplate restTemplate; 
```
Метод getNasaInformationObject() возвращает объект InformationObject преобразованный
из JSON полученного GET запросом к NASA API через обертку запроса ResponseEntity<>.
```java
    public InformationObject getNasaInformationObject() {

        ResponseEntity<InformationObject> responseEntity = restTemplate.exchange(REMOTE_SERVICE_URL + TOKEN,
                HttpMethod.GET, null, new ParameterizedTypeReference<InformationObject>() {});
        InformationObject nasaToDay = responseEntity.getBody();

        return nasaToDay;
    }
```
####Описание DownloaderImpl.java
Было принято решение отделить функционал загрузки в отдельный и класс интерфейс, на 
случай изменения хранения файлов в NASA API и предоставить реализацию интерфейса
которая с помощью класса из стандартной библиотеки Java
java.nio.file.Files.java с помощью метода copy() копирует файл находящийся 
по ссылке поля url экземпляра объекта полученного из JSON.

```java
@Component
public class DownloaderImpl implements Downloader{

    public void downloadFileNasa(InformationObject nasaObject) {
        String[] url = nasaObject.getUrl().split("/");
        String fileName = url[url.length - 1];
        Path path = Path.of("./src/main/resources/NASAFiles/" + fileName);

        try (InputStream in = new URL(nasaObject.getUrl()).openStream()) {
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
```
####Описание InformationObject.java
Обычный POJO описывающий JSON. Аннотация @com.fasterxml.jackson.annotation.JsonProperty в 
конструкторе используется для автоматического связывания полей объекта с полями JSON документа.









