use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

fn main() {
    let mut sum = 0;
    if let Ok(lines) = read_lines("./day01.txt"){
        for line in lines{
            if let Ok(ip) = line{
                let mut index = 0;
                let mut tot = String::new();

                while !ip.as_bytes()[index].is_ascii_digit() {
                    index+=1;
                }

                if let Some(character) = ip.chars().nth(index) {
                    tot.push(character);
                } else {
                    println!("Index out of range or no character found at index");
                }

                index = ip.len()-1;

                while !ip.as_bytes()[index].is_ascii_digit() {
                    index-=1;
                }

                if let Some(character) = ip.chars().nth(index) {
                    tot.push(character);
                } else {
                    println!("Index out of range or no character found at index");
                }

                if let Ok(result) = tot.parse::<usize>() {
                    sum += result;
                }
            }
        }

        println!("\nPart 1: {}", sum);
    }
}

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}