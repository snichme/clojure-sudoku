Vagrant.configure(2) do |config|

  config.vm.box = "https://cloud-images.ubuntu.com/vagrant/trusty/current/trusty-server-cloudimg-amd64-vagrant-disk1.box"

  # config.vm.network "forwarded_port", guest: 10555, host: 10555
  # config.vm.network "forwarded_port", guest: 9001, host: 9001
  # config.vm.network "forwarded_port", guest: 3449, host: 3449
  # config.vm.network "forwarded_port", guest: 5000, host: 5000

  config.vm.provision :shell, path: "vagrant-setup.sh"
end
