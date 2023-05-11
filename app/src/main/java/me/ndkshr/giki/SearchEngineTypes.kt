package me.ndkshr.giki

enum class SearchEngineTypes(val domain: String) {
    Bing(domain = "bing"), Google(domain = "google");
    companion object {
        fun getName(position: Int): String {
            return when (position) {
                0 -> SearchEngineTypes.Bing.domain
                else -> SearchEngineTypes.Google.domain
            }
        }
    }
}