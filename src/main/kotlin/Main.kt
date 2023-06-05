data class Likes(
    val count: Int = 0, // Количество лайков
    val userLikes: Boolean = false, // Лайкнул ли пользователь данный пост
    val canLike: Boolean = true, // Может ли пользователь поставить лайк
    val canPublish: Boolean = true // Может ли пользователь сделать репост
)

data class Comments(
    val count: Int = 0, // Количество комментариев
    val canPost: Boolean = true, // Может ли пользователь оставить комментарий
    val groupsCanPost: Boolean = true, // Могут ли сообщества оставлять комментарии
    val canClose: Boolean = false, // Может ли пользователь закрыть комментарии к посту
    val canOpen: Boolean = false // Может ли пользователь открыть комментарии к посту
)

data class Post(
    val id: Int, // Идентификатор поста
    val ownerId: Int, // Идентификатор владельца поста
    val fromId: Int, // Идентификатор автора поста
    val createdBy: Int? = null, // Идентификатор администратора, создавшего пост (если применимо)
    val date: Int, // Время создания поста (в формате Unix time)
    val text: String, // Текст поста
    val likes: Likes, // Информация о лайках к посту
    val comments: Comments, // Информация о комментариях к посту
    val canEdit: Boolean = true, // Может ли пользователь редактировать пост
    val canDelete: Boolean = true, // Может ли пользователь удалить пост
    val canPin: Boolean = true, // Может ли пользователь закрепить пост
    val isPinned: Boolean = false, // Является ли пост закрепленным
    val markedAsAds: Boolean = false // Помечен ли пост как реклама
)

object WallService {
    private var posts = emptyArray<Post>() // Массив для хранения постов

    // Добавление поста
    fun addPost(post: Post): Post {
        posts += post
        return post
    }

    // Обновление поста
    fun updatePost(updatedPost: Post): Boolean {
        val index = posts.indexOfFirst { it.id == updatedPost.id }
        if (index == -1) return false

        posts[index] = updatedPost
        return true
    }

    // Удаление поста
    fun removePost(postId: Int): Boolean {
        val index = posts.indexOfFirst { it.id == postId }
        if (index == -1) return false

        posts = posts.filterIndexed { i, _ -> i != index }.toTypedArray()
        return true
    }

    // Получение всех постов
    fun getPosts(): List<Post> {
        return posts.toList()
    }
}

fun main() {
    val post1 = Post(
        id = 1,
        ownerId = 123,
        fromId = 456,
        date = 1630550400,
        text = "Hello, world!!",
        likes = Likes(count = 10),
        comments = Comments(count = 5)
    )

    WallService.addPost(post1)

    val post2 = Post(
        id = 2,
        ownerId = 123,
        fromId = 789,
        date = 1630551200,
        text = "This is another post",
        likes = Likes(count = 15),
        comments = Comments(count = 2)
    )

    WallService.addPost(post2)

    val allPosts = WallService.getPosts()
    for (post in allPosts) {
        println(post.text)
    }
}
