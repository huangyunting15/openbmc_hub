SUMMARY = "Phosphor post code manager"
DESCRIPTION = "Phosphor post Code Manager monitors post code posted on dbus \
interface /xyz/openbmc_project/state/boot/raw by snoopd daemon and save them \
in a file under /var/lib for history."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"
SRCREV = "e7e197b3afc0ce14c5fcbf5c1da00dd04b65e2aa"
PV = "1.0+git${SRCPV}"

SRC_URI = "git://github.com/openbmc/phosphor-post-code-manager.git;branch=master;protocol=https"

S = "${WORKDIR}/git"

inherit meson pkgconfig systemd

def get_service(d):
    return " ".join(["xyz.openbmc_project.State.Boot.PostCode@{}.service".format(x) for x in d.getVar('OBMC_HOST_INSTANCES').split()])

SYSTEMD_SERVICE:${PN} = "${@get_service(d)}"
DEPENDS += " \
    sdbusplus \
    phosphor-dbus-interfaces \
    phosphor-logging \
    libcereal \
    "
FILES:${PN}  += "${systemd_system_unitdir}/xyz.openbmc_project.State.Boot.PostCode@.service"
FILES:${PN}  += "${systemd_system_unitdir}/xyz.openbmc_project.State.Boot.PostCode.service"
