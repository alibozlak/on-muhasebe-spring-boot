## Project Notes :

- This project is **DB-First**. (Not Code-First)
- This project runs Spring Data JDBC for persistence.
- **No foreign key constraints anywhere.** Not code side (no association annotations, no
  `@JoinColumn`) and not RDBMS side either - the production database does not declare them,
  and neither does `src/test/resources/schema.sql`. Columns such as `accounts.user_id` or
  `admins.user_id` are *logical* references: the database will happily store a row pointing
  at an id that does not exist. Keeping those references valid is the application's job, and
  in the AI test pipeline it is the job of the DB verification steps in
  `src/test/resources/ai_instructions/` - a broken reference fails no constraint, so nothing
  reports it unless we check for it.
- 2026.07.16: Entities **public not final fields** and DTO's **public final fields** + **just AllArgsConstructor** ([Turkish Medium essay](https://medium.com/@aliBozlak/javada-record-gibi-bir-contextual-keyword-%C3%BCretme-ihtiyac%C4%B1m-8a1e0d81b0ab))