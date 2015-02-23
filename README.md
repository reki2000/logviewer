# LogViewer

Logviewer is a web access log viewer.

* Enough lightweight for over 100MB logs
* Fast parallel log collecting among multiple servers
* Supports typical access log formats like apache CustomLog and LTSV.
* Extendable for Add-ons for user defined script (ex. specific conversion like session-id to user-id)
* Java8 and JavaFX

# Todo

* [ ] Make clear how to handle cookie, with some interesting one (ex. session-id) and not.
* [ ] Make clear how to specify search conditions with start date, end date and keywords
* [ ] Make clear how to pass specified parameters to external scripts
* [x] Implement a concept proving parallel log collectors with external command
* [ ] Implement debugging console for console window
* [ ] Implement error notification for command invocation in console window
* [ ] Implement error notification for parsing in console window
* [ ] Implement console in UI
* [ ] Prepare big gzipped logfile (100M) and measure memory usage
* [ ] Implement parallel task progress view in UI
* [ ] Make it configurable for special cookies
* [ ] Make it configurable for external commands
* [ ] Dependencies should be managed by Maven
* [ ] Apache date format parser
* [ ] Optional timestamp handling for sorting
* [ ] Cookie parser

# License

Apache 2.0

Copyright reki2000@github.com

