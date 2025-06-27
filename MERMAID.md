# Module Graph

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  subgraph :adapters
    :adapters:repositories["repositories"]
  end
  subgraph :business
    :business:entity["entity"]
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