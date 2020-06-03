# Virtual Topic 사용법

## Active MQ Configuration

### useVirtualTopics 추가

`broker`태그에 `useVirtualTopics`속성을 `true`로 설정하여 추가한다.

### destinationInterceptors 추가

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

### 전체 activemq.xml 파일

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

## 일반 Topic 전송 방법

예를 들어 `Pulblisher`의 `destination`을 `VirtualTopic.TEST`으로 전송하는 경우 일반적인 Pub-Sub 모델로 메시지를 수신하려면 수신자의 `destination`을 `VirtualTopic.TEST`으로 정하면 된다.

Pulblisher 예시

```kotlin
@Component
class MessageSender(private val jmsTemplate: JmsTemplate) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun sendVirtual(message: SampleData) {
        logger.info("Sender Message -> [$message]")
        jmsTemplate.convertAndSend("VirtualTopic.TEST", message)
    }
}

data class SampleData(
        val body: String = ""
) : Serializable
```

Pulblisher application.yml 예시

```yml
spring:
  jms:
    pub-sub-domain: true
  activemq:
    broker-url: tcp://localhost:61616
    user: admin
    password: admin
```

Subscriber 예시

```kotlin
@Component
class MessageListener {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @JmsListener(destination = "VirtualTopic.TEST")
    fun listen3(message: SampleData) {
        logger.info("Server3 Virtual1 - > $message")
    }

    @JmsListener(destination = "VirtualTopic.TEST")
    fun listen4(message: SampleData) {
        logger.info("Server3 Virtual2 - > $message")
    }
}

data class SampleData(
        val body: String = ""
) : Serializable
```

Subscriber application.yml 예시

```yml
spring:
  jms:
    pub-sub-domain: true
  activemq:
    broker-url: tcp://localhost:61616
    user: admin
    password: admin
```

## Virtural Topic 전송방법

예를 들어 `Pulblisher`의 `destination`을 `VirtualTopic.TEST`으로 전송하고 `Subscriber`는 `destination`을 `Consumer.{Client-ID}.VirtualTopic.TEST`로 지정하여 사용합니다.

`Client-ID`별로 별도 메시지를 수신할 수 있다.

Pulblisher 예시

```kotlin
@Component
class MessageSender(private val jmsTemplate: JmsTemplate) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun sendVirtual(message: SampleData) {
        logger.info("Sender Message -> [$message]")
        jmsTemplate.convertAndSend("VirtualTopic.TEST", message)
    }
}

data class SampleData(
        val body: String = ""
) : Serializable
```

Pulblisher application.yml 예시

```yml
spring:
  jms:
    pub-sub-domain: true
  activemq:
    broker-url: tcp://localhost:61616
    user: admin
    password: admin
```

Subscriber 예시

```kotlin
@Component
class MessageListener {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @JmsListener(destination = "Consumer.test.VirtualTopic.TEST")
    fun listen4(message: SampleData) {
        logger.info("Server1 Virtual2 - > $message")
    }
}

data class SampleData(
        val body: String = ""
) : Serializable
```

Subscriber application.yml 예시

```yml
spring:
  jms:
    pub-sub-domain: false
  activemq:
    broker-url: tcp://localhost:61616
    user: admin
    password: admin
```

# Dead Letter Queues 설정 방법

## Active MQ Configuration

```xml
<policyEntry queue=">">
  <deadLetterStrategy>
      <individualDeadLetterStrategy queuePrefix="DLQ." useQueueForQueueMessages="true"/>
  </deadLetterStrategy>
</policyEntry>
```

## 전체 설정 예시

```xml
<broker xmlns="http://activemq.apache.org/schema/core" brokerName="localhost" dataDirectory="${activemq.data}" useVirtualTopics="true">
  <destinationPolicy>
      <policyMap>
        <policyEntries>
          <policyEntry queue=">">
          <deadLetterStrategy>
              <individualDeadLetterStrategy queuePrefix="DLQ." useQueueForQueueMessages="true"/>
          </deadLetterStrategy>
          </policyEntry>
        </policyEntries>
      </policyMap>
  </destinationPolicy>
</broker>
```
