plugins {
	id("com.android.library") version "8.1.0" apply false
	id("com.android.application") version "8.1.0" apply false
	id("org.jetbrains.kotlin.android") version "1.8.20" apply false
	id("com.google.dagger.hilt.android") version "2.47" apply false
}

tasks.register<Delete>("clean").configure {
	delete(rootProject.buildDir)
}