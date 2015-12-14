use strict;
use warnings;

# day 8 - counting letters (work in progress)

open (my $fh, '<', $ARGV[0]) or die "Error opening file: $!\n";

foreach my $line (<$fh>) {
	chomp $line;
	my $abs_len = length($line);
	my @letters = split //, $line;
	

	print "$abs_len\n";
}
