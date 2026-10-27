# Input Device Configuration for the keyboard-side touch panel
# Device: touch_kbd
#
# This TP is mapped directly to the main display as a secondary touchscreen.
# It sends normal touch events (DOWN/MOVE/UP), so any scrollable UI on the
# main screen can be slid with a finger on this pad.

# Treat as a touchscreen and associate with the main display.
touch.deviceType = touchScreen

# Associate with the internal (main) display viewport.
device.internal = 1

# Allow the device to work while the viewport is not yet active.
touch.enableForInactiveViewport = 1
