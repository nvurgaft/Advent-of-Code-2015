use strict;
use warnings;

use Digest::MD5 qw(md5_hex);

# day 4 - md5's

my $input = "bgvyzdsv";
my $attempt = 0;
my $result;
while ($attempt < 9000000) {
	$result = md5_hex($input . $attempt);
	#print "iter: $attempt => result: $result\n";
	if (index($result, "000000")==0) {
		print "found it sir!!!\n";
		print "iteration: $attempt\n";
		last;
	}
	++$attempt;
}

print "$result\n";
