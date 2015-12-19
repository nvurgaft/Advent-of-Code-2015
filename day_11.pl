use strict;
use warnings;

# day 11 - santa makes a bad sysadmin

my $in = "vzbxkghb"; # your input may vary

my $valid = 0;
while ($valid eq 0) {

	my @input = split //, $in;
	$valid = 1;
	my $len = @input;

	for (@input) {
		if ($_ eq 'i' or $_ eq 'o' or $_ eq 'l') {
			$valid = 0;
			last;
		}
	}

	if ($valid) {
		$valid = 0;
		for (my $i=0; $i<$len-2; $i++) {
			if ((ord($input[$i+2])-ord($input[$i+1]) eq 1) and (ord($input[$i+1])-ord($input[$i]) eq 1)) {
				$valid = 1;
				last;
			}
		} 
	}

	if ($valid) {
		$valid = 0;
		my $amount = 0;
		for (my $i=0; $i<$len-1; $i++) {
			if ($input[$i] eq $input[$i+1]) {
				++$amount;
				++$i;
			}
			if ($amount eq 2) {
				$valid = 1;
				last;
			}
		}
	}

	# printing yout result after every iteration is a good practice in wasting time
	#print "the password $in is " . ($valid ? "valid" : "invalid") . "\n";
	++$in if $valid eq 0;
}
print "santa's next password is $in\n";
