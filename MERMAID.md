### Module Graph - Database

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  :composeApp["composeApp"]
  subgraph :datasource
    :datasource:database["database"]
  end

  :composeApp --> :datasource:database

classDef focus fill:#769566,stroke:#fff,stroke-width:2px,color:#fff;
class :datasource:database focus
```
### Module Graph - Network

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  :composeApp["composeApp"]
  subgraph :business
    :business:entity["entity"]
  end
  subgraph :adapters
    :adapters:repositories["repositories"]
  end
  subgraph :datasource
    :datasource:network["network"]
    :datasource:database["database"]
  end

  :composeApp --> :business:entity
  :composeApp --> :adapters:repositories
  :composeApp --> :datasource:network
  :composeApp --> :datasource:database
```
### Module Graph - Repository

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  :composeApp["composeApp"]
  subgraph :business
    :business:entity["entity"]
  end
  subgraph :adapters
    :adapters:repositories["repositories"]
  end
  subgraph :datasource
    :datasource:network["network"]
    :datasource:database["database"]
  end

  :composeApp --> :business:entity
  :composeApp --> :adapters:repositories
  :composeApp --> :datasource:network
  :composeApp --> :datasource:database
```
### Module Graph - Entity

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  :composeApp["composeApp"]
  subgraph :business
    :business:entity["entity"]
  end
  subgraph :adapters
    :adapters:repositories["repositories"]
  end
  subgraph :datasource
    :datasource:network["network"]
    :datasource:database["database"]
  end

  :composeApp --> :business:entity
  :composeApp --> :adapters:repositories
  :composeApp --> :datasource:network
  :composeApp --> :datasource:database
```
### Module Graph - Compose

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  :composeApp["composeApp"]
  subgraph :business
    :business:entity["entity"]
  end
  subgraph :adapters
    :adapters:repositories["repositories"]
  end
  subgraph :datasource
    :datasource:network["network"]
    :datasource:database["database"]
  end

  :composeApp --> :business:entity
  :composeApp --> :adapters:repositories
  :composeApp --> :datasource:network
  :composeApp --> :datasource:database

classDef android-application fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :composeApp android-application
class :business:entity unknown
class :adapters:repositories unknown
class :datasource:network unknown
class :datasource:database unknown

```
# Primary Graph - Compose 1

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph TB
  :composeApp["composeApp"]
  subgraph :business
    :business:entity["entity"]
  end
  subgraph :adapters
    :adapters:repositories["repositories"]
  end
  subgraph :datasource
    :datasource:network["network"]
    :datasource:database["database"]
  end

  :composeApp --> :business:entity
  :composeApp --> :adapters:repositories
  :composeApp --> :datasource:network
  :composeApp --> :datasource:database

classDef android-application fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
classDef unknown fill:#676767,stroke:#fff,stroke-width:2px,color:#fff;
class :composeApp android-application
class :business:entity unknown
class :adapters:repositories unknown
class :datasource:network unknown
class :datasource:database unknown

```