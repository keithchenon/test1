
package org.paumard.async;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.paumard.async.model.User;

public class AsyncExampleMultiBranchEither {

	public static void main(String[] args) {

		ExecutorService executor1 = Executors.newSingleThreadExecutor();
		
		Supplier<List<Long>> supplyIDs = () -> { 
			sleep(200);
			return Arrays.asList(1L, 2L, 3L);
		};

		Supplier<List<Long>> supplyIDs2 = () -> {
			sleep(200);
			return Arrays.asList(4L, 5L, 6L);
		};

		Function<List<Long>, CompletableFuture<List<User>>> fetchUsers1 = ids -> { 
			sleep(150);
			Supplier<List<User>> userSupplier = 
					() -> ids.stream().map(User::new).collect(Collectors.toList());
			return CompletableFuture.supplyAsync(userSupplier);
		};

		Function<List<Long>, CompletableFuture<List<User>>> fetchUsers2 = ids -> { 
			sleep(5000);
			Supplier<List<User>> userSupplier = 
					() -> ids.stream().map(User::new).collect(Collectors.toList());
			return CompletableFuture.supplyAsync(userSupplier);
		};
		
		Consumer<List<User>> displayer = users -> users.forEach(System.out::println); 
		
		CompletableFuture<List<Long>> completableFuture = CompletableFuture.supplyAsync(supplyIDs);
		CompletableFuture<List<Long>> completableFuture2 = CompletableFuture.supplyAsync(supplyIDs2);
		CompletableFuture<List<User>> users1 = completableFuture.thenComposeAsync(fetchUsers1);
		CompletableFuture<List<User>> users2 = completableFuture2.thenComposeAsync(fetchUsers2);
		
		users1.thenRun(() -> System.out.println("Users 1"));
		users2.thenRun(() -> System.out.println("Users 2"));
		
//		users1.acceptEither(users2, displayer);
		users1.thenAcceptBoth(users2, (u1,u2)-> {u1.forEach(System.out::println);
		u2.forEach(System.out::println);});
		sleep(6_000);
		executor1.shutdown();
	}

	private static void sleep(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
		}
	}
}
