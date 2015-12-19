use strict;
use warnings;

# day 10 - elves playing silly math games

my $input = "1321131112"; # your input may vary

print "please wait, this may take a while...\n";
for my $itr (1..50) {
	my @in = split //, $input;
	my @parts;
	my $len = @in;
	my $j=0;
	for (my $i=1; $i<$len; $i++) {
		if ($in[$i] ne $in[$i-1]) {
			push (@parts, substr($input, $j, $i-$j));
			$j=$i;
		}
	}
	push (@parts, substr($input, $j, $len));

	$input = "";
	foreach (@parts) {
		$input .= length($_) . substr($_, 0, 1);
	}
}

print "result length is " . length($input) . "\n";
