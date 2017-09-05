# No More Mock Servers (NMM)

In the universe of Micro Service, it is not the first time I was asked to build a mock server by the QA teams, when I need to ship a feature to DEMO/STAGE environment, which
 - hardly returns the expected response to cover a scenario rarely happens
 - requires some specific input from micro services not supposed to concern
 - is non-backward compatible for some consumers that are still under development

I usually need to turn off such feature on QA environments, when multiple QA teams complaint being blocked by our service, or the nightly regression fails. As a nice guy, I do not like it.

## The Structure

This project provides a simple(ME NOT INVOLVED) way to customize the response, based on the incoming query. The basic components are **Server**, **Client** and **Event Bus**:

- At **server** side:
  - accept POST request, in which a scenario and according static response is defined
  - respond the meta data for scenario, for tracking purposes
  - accept http request, in which a scenario is dropped based on scenario id
  - persist or remove the `(scenario, response)` tuple
  - publish what happened to a *event bus*
- At **Event Bus**:
  - create a topic for each `${service}:${interface}`
  - each event is published by *server* and subscribed by concerning *clients*
- At **Client** side:
  - proxy the incoming traffic, at interface level, in less intrusive way(annotation, for instance)
  - analyze the query, return specific static response for matched predefined scenario
  - cache the scenarios when server starts and the route is registered
  - subscribe to the *event bus* to monitor the scenario creation, update and deletion
  - become transparent at PRODUCTION

## Getting Started

## Tech Design

- [The choice of message bus](https://github.com/EdisonRen/nmm/wiki/Choice-of-Message-Bus)
- [The choice of persistence layer](https://github.com/EdisonRen/nmm/wiki/Choice-of-Persistence-Layer)

## TO BE ADDED TO WIKI

- How to declare a Scenario

- Prevent inconsistence between client and server caused by race condition

- Authorization

- Security

### Licenses

NMM is under the MIT license. See the [LICENSE](LICENSE) file for details.
