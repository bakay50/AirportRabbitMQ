package it.blog.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import it.blog.rabbitmq.message.CurrencyRate;
import it.blog.rabbitmq.message.Passenger;
import it.blog.rabbitmq.message.PilotCommunication;
import it.blog.rabbitmq.message.WeatherForecast;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Profile("airplane")
public class FlightReceiver {

	@RabbitListener(queues = "${flight.code}")
	public void listenDead(Message message) throws Exception {

		log.info("Message read is {}", message);

		Object receivedObject = new Jackson2JsonMessageConverter().fromMessage(message);
		/*
		 * Passenger
		 */
		if (receivedObject instanceof Passenger) {
			Passenger passenger = (Passenger) receivedObject;

			log.info("Received CheckIn {}", passenger);
		}
		/*
		 * Currency
		 */
		if (receivedObject instanceof CurrencyRate) {
			CurrencyRate currency = (CurrencyRate) receivedObject;

			log.info("Current currency applied {}", currency.getCurrency());
		}
		/*
		 * Weather
		 */
		if (receivedObject instanceof WeatherForecast) {
			WeatherForecast weatherForecast = (WeatherForecast) receivedObject;

			log.info("Weather conditions {}", weatherForecast);
		}
		/*
		 * PilotCommunication
		 */
		if (receivedObject instanceof PilotCommunication) {
			PilotCommunication pilotCommunication = (PilotCommunication) receivedObject;

			log.info("Communication for Pilot {}", pilotCommunication);
		}

	}
}
