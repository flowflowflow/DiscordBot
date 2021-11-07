import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.IOHelper;

public class DiscordBot {
    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger("DiscordBot.class");
        final String token = IOHelper.readToken();
        final GatewayDiscordClient client = DiscordClientBuilder.create(token).build()
                .login()
                .block();

        try {
            new GlobalCommandRegistrar(client.getRestClient()).registerCommands();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Failed registering commands", e);
            System.exit(-1);
        }


        client.on(ChatInputInteractionEvent.class, event -> {
            // logic
            if(event.getCommandName().equals("ping")) {
                return event.reply("Pong :)!");
            }
            else return null;
        }).subscribe();


        client.onDisconnect().block();
/*
        System.out.println("=============================");

        Mono.just("LUL").map(String::length).subscribe(System.out::println);
        Flux.just('O', 'M', 'E', 'G', 'A', 'L', 'U', 'L').subscribe(System.out::print);


        Mono.just("Hello World")
                .flatMap(aString -> Mono.just(aString.length()))
                .subscribe(System.out::println); // prints 11

        Flux.just("Hello", "World")
                .flatMap(aString -> Flux.just(aString.length(), 42))
                .subscribe(System.out::println);


        System.out.println("=============================");
        login.block();
*/


    }
}
