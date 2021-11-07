import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ApplicationCommandInteractionEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.IOHelper;

public class DiscordBot {
    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger("DiscordBot.class");
        final String token = IOHelper.readToken();
        final long guildId = IOHelper.readGuildId();

        logger.info("Bot token: " + token + " GuildID: " + guildId);

        final GatewayDiscordClient client = DiscordClientBuilder.create(token).build()
                .login()
                .block();

        try {
            //new GlobalCommandRegistrar(client.getRestClient()).registerCommands();
            new GuildCommandRegistrar(client.getRestClient()).registerCommands();
        } catch (Exception e) {
            logger.error("Failed registering commands", e);
            System.exit(-1);
        }


        client.on(ChatInputInteractionEvent.class, event -> {
            if(event.getCommandName().equals("ping")) {
                logger.info("event received");
                return event.reply("Pong!");
            }
            else return null;
        }).subscribe();


        client.onDisconnect().block();

    }
}
