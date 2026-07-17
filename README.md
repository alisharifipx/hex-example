# hex-example — Privacy Policy

My interpretation of **true Hexagonal (Ports & Adapters) Architecture** in Spring Boot.

This is a learning/reference repo, not a template. It shows how I read Alistair Cockburn's
Ports & Adapters pattern applied to a small Privacy Policy CRUD service. It is deliberately
"textbook hex" domain at the core, everything external as an adapter.

Because it aims for the pure form, some structure here is heavier than a real feature needs
(one port per operation, mappers between every representation). That verbosity is on purpose
to make each hex concept visible in isolation.

## Stack

- Java 21, Spring Boot 3.3.2
- Spring Web (inbound HTTP adapter)
- Spring Data JPA + H2 in-memory DB (outbound persistence adapter)

## The one rule

Dependencies point **inward**. The core (`domain` + `core`) knows nothing about Spring, HTTP,
or JPA. Adapters depend on the core's ports; the core never depends on an adapter.

```
HTTP  →  inbound adapter  →  inbound port  →  service  →  outbound port  →  outbound adapter  →  DB
        (controller)        (use case)      (core)      (interface)        (JPA)
```

## Layout

```
com.example.privacypolicy/
├── domain/                     # pure business model — no framework
│   ├── model/                  #   PrivacyPolicy (record), PrivacyPolicySummary
│   └── exception/              #   domain invariant failures
├── core/                       # the application core (inside the hexagon)
│   ├── port/inbound/           #   use-case interfaces — entry points into the core
│   ├── port/outbound/          #   interfaces the core needs (load/save) — one per operation
│   ├── service/                #   use-case implementations
│   └── exception/              #   application-level (e.g. PrivacyPolicyNotFound)
└── adapter/                    # everything external (outside the hexagon)
    ├── inbound/http/           #   REST controller + Request/Response DTOs — CALL inbound ports
    └── outbound/persistence/   #   JPA entity, repository, mapper — IMPLEMENT outbound ports
```

## Key ideas shown here

- **Domain is pure.** `PrivacyPolicy` is a plain Java `record` with its invariants enforced
  in the constructor. No `@Entity`, no annotations. It changes only when the business changes.
- **Ports are owned by the core.** Inbound ports (use cases) are *implemented* by core services
  and *called* by adapters. Outbound ports are *defined* by the core and *implemented* by adapters.
- **One outbound port per operation** (`LoadPrivacyPolicyPort`, `SavePrivacyPolicyPort`, …) so a
  use case depends only on what it uses - `GetPrivacyPolicyService` can't even call `save()`.
- **Adapters translate.** The persistence adapter maps between `PrivacyPolicy` (domain) and
  `PrivacyPolicyJpaEntity` via `PrivacyPolicyMapper`. Entities never leave the adapter.
- The persistence adapter is named `...MssqlAdapter` to mirror the target DB; the demo runs on
  H2 so it starts with no external database.

## Run

```bash
mvn spring-boot:run
```

Then hit the API (H2 starts empty):

```bash
# create
curl -X POST localhost:8080/api/privacy-policies \
  -H 'Content-Type: application/json' \
  -d '{"content":"v1 text","effectiveFrom":"2026-01-01","effectiveTo":null}'

# get
curl localhost:8080/api/privacy-policies/1
```
