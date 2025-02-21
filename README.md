# sponsorblock-kt

[![Maven Central Version](https://img.shields.io/maven-central/v/dev.zt64.sbkt/client)](https://central.sonatype.com/artifact/dev.zt64/sbkt/client)
<br>
![badge-platform-jvm]
![badge-platform-js]
![badge-platform-js-node]
![badge-platform-linux]
![badge-platform-windows]
![badge-platform-macos]
![badge-platform-ios]
![badge-platform-tvos]
![badge-platform-watchos]

A Kotlin Multiplatform client library for
the [SponsorBlock API](https://wiki.sponsor.ajay.app/w/API_Docs), powered
by [Ktor](https://github.com/ktorio/ktor).

## Setup

Add the following to your `gradle/libs.versions.toml`:

```toml
[versions]
sbkt = "x.y.z"  # Replace with latest version

[libraries]
sbkt-client = { module = "dev.zt64.sbkt:client", version.ref = "sbkt" }
# or
sbkt-core = { module = "dev.zt64.sbkt:core", version.ref = "sbkt" }
```

Then add the dependency to your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation(libs.sbkt.client) // Full implementation with Ktor
    // or
    implementation(libs.sbkt.core)   // Core functionality only
}
```

> **Note:** You must include a Ktor engine implementation in your dependencies to use the client.

## Quick Start

```kotlin
// Create an unauthenticated guest client
val client = SponsorBlockClient()

// Get segments for a video
val segments = client.getSegments(VIDEO_ID)

// Create a client with a user ID
val userClient = SponsorBlockClient.user(USER_LOCAL_ID)

// Vote on segments
userClient.upvoteSegment(segmentUuid, videoId)
```

## License

This project is licensed under the [GPL v3.0](LICENSE) license.

[badge-platform-jvm]: http://img.shields.io/badge/-jvm-DB413D.svg?style=flat

[badge-platform-js]: http://img.shields.io/badge/-js-F8DB5D.svg?style=flat

[badge-platform-js-node]: https://img.shields.io/badge/-nodejs-68a063.svg?style=flat

[badge-platform-linux]: http://img.shields.io/badge/-linux-2D3F6C.svg?style=flat

[badge-platform-windows]: http://img.shields.io/badge/-windows-4D76CD.svg?style=flat

[badge-platform-macos]: http://img.shields.io/badge/-macos-111111.svg?style=flat

[badge-platform-ios]: http://img.shields.io/badge/-ios-CDCDCD.svg?style=flat

[badge-platform-tvos]: http://img.shields.io/badge/-tvos-808080.svg?style=flat

[badge-platform-watchos]: http://img.shields.io/badge/-watchos-C0C0C0.svg?style=flat

[badge-platform-wasm]: https://img.shields.io/badge/-wasm-624FE8.svg?style=flat