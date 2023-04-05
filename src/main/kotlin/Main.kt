import com.jessecorbett.diskord.bot.bot
import com.jessecorbett.diskord.bot.classicCommands
import com.jessecorbett.diskord.bot.events
import com.jessecorbett.diskord.bot.interaction.interactions
import com.jessecorbett.diskord.util.sendMessage

suspend fun main(args: Array<String>) {
    sampleInteractionsBot()
//    sampleEventBot()
//    sampleClassicCommands()
}

private suspend fun sampleInteractionsBot() {
    bot(TOKEN) {
        interactions {
            slashCommand("echo", "따라 말하기") {
                val message by stringParameter("message", "하고 싶은 말 입력")
                callback { interaction, _ ->
                    interaction.respond {
                        content = message
                    }
                }
            }

            slashCommand("introduce", "자기소개") {
                callback { interaction, _ ->
                    interaction.respond {
                        content = "저는 백승민님의 충실한 하인 채종인입니다."
                    }
                }
            }
        }
    }
}

private suspend fun sampleEventBot() {
    bot(TOKEN) {
        events {
            onGuildMemberAdd {
                channel("").sendMessage("Welcome to the server, ${it.user?.username}!")
            }
            onChannelCreate {
                channel(it.id).sendMessage("채널 생성했네")
            }
        }
    }
}

private suspend fun sampleClassicCommands() {
    bot(TOKEN) {
        classicCommands {
            command("ping") {
                it.respond("pong")
            }
        }
    }
}