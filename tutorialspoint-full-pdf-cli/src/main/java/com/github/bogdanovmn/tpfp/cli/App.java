package com.github.bogdanovmn.tpfp.cli;

import com.github.bogdanovmn.tpfp.lib.domain.Article;
import com.github.bogdanovmn.tpfp.lib.domain.TutorialsPoint;
import org.apache.commons.cli.*;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException {
		Options cliOptions = new Options();
		cliOptions
			.addOption(
				Option.builder("a")
					.longOpt("article")
					.desc("article path name")
					.required()
					.hasArg()
					.argName("NAME")
					.build()
			)
			.addOption(
				Option.builder("o")
					.longOpt("output")
					.desc("output file name")
					.required()
					.hasArg()
					.argName("FILE NAME")
					.build()
			);

		CommandLineParser cmdLineParser = new DefaultParser();
		try {
			CommandLine cmdLine = cmdLineParser.parse(cliOptions, args);
			String articlePathName = cmdLine.getOptionValue("article");
			Article article = new TutorialsPoint().getArticle(articlePathName);
			article.toPdf(cmdLine.getOptionValue("output"));

		}
		catch (ParseException e) {
			showErrWithUsage(e.getMessage(), cliOptions);
		}
	}

	private static void showErrWithUsage(final String message, final Options options) {
		System.out.println(message);
		showUsage(options);
	}

	private static void showUsage(final Options options) {
		new HelpFormatter()
			.printHelp("app", options, true);
	}
}
