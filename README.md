# Active MQ Configuration

## useVirtualTopics 추가

`broker`태그에 `useVirtualTopics`속성을 `true`로 설정하여 추가한다.

## destinationInterceptors 추가

아래와 같이 `destinationInterceptors`태그를 추가한다.

```xml
<destinationInterceptors>
    <virtualDestinationInterceptor>
        <virtualDestinations>
        <virtualTopic name="VirtualTopic.>" prefix="Consumer.*." selectorAware="false"/>
        </virtualDestinations>
    </virtualDestinationInterceptor>
</destinationInterceptors>
```

- `virtualTopic` 태그의 `name` 속성은 `Publisher`의 `destination` 명칭에 대한 패턴이다.
- `virtualTopic` 태그의 `prefix` 속성은 `Subscriber`의 `destination` 명칭에 대한 패턴이다.

## 전체 activemq.xml 파일

```xml
<!-- activemq.xml -->
<broker xmlns="http://activemq.apache.org/schema/core" brokerName="localhost" dataDirectory="${activemq.data}" useVirtualTopics="true">

    <destinationInterceptors>
        <virtualDestinationInterceptor>
            <virtualDestinations>
            <virtualTopic name="VirtualTopic.>" prefix="Consumer.*." selectorAware="false"/>
            </virtualDestinations>
        </virtualDestinationInterceptor>
    </destinationInterceptors>

    <!-- 이하 생략 -->
</broker>
```
